import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
 
  const [tickets, setTickets] = useState([]);
  const [description, setDescription] = useState("");
  const [customer, setCustomer] = useState("");
  const [loading, setLoading] = useState(false);

  const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";
  const fetchTickets = async () => {
    try {
      const res = await axios.get(`${API_URL}/api/tickets`);
      setTickets(res.data);
    } catch (err) {
      console.error("Error connecting to Java backend:", err);
    }
  };

  
  useEffect(() => { fetchTickets(); }, []);

  
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await axios.post(`${API_URL}/api/tickets`, {
        customerName: customer,
        description: description
      });
      setDescription("");
      setCustomer("");
      fetchTickets(); 
    } catch (err) {
      alert("Failed to create ticket. Is the backend running?");
    }
    setLoading(false);
  };

  return (
    <div className="container">
      <header className="header">
        <h1>☁️ SmartTicket</h1>
        <p>Smart Agentforce Demo</p>
      </header>

      <div className="main-content">
        {/* Input Form */}
        <div className="card form-card">
          <h2>New Support Case</h2>
          <form onSubmit={handleSubmit}>
            <div className="input-group">
              <label>Customer Name</label>
              <input 
                value={customer} 
                onChange={e => setCustomer(e.target.value)} 
                placeholder="Ex: Acme Corp"
                required 
              />
            </div>
            <div className="input-group">
              <label>Issue Description</label>
              <textarea 
                value={description} 
                onChange={e => setDescription(e.target.value)} 
                placeholder="Ex: System crashed during checkout..."
                required 
                rows="4"
              />
            </div>
            <button type="submit" disabled={loading} className="submit-btn">
              {loading ? "AI is Analyzing..." : "Submit Case"}
            </button>
          </form>
        </div>

        {/* List of Tickets */}
        <div className="ticket-list">
          <h2>Case Queue ({tickets.length})</h2>
          {tickets.map(t => (
            <div key={t.id} className={`ticket-card priority-${t.aiPriority?.toLowerCase()}`}>
              <div className="ticket-header">
                <span className="customer">{t.customerName}</span>
                <span className={`badge ${t.aiPriority}`}>{t.aiPriority || "PENDING"}</span>
              </div>
              <p className="issue"><b>Issue:</b> {t.description}</p>
              <div className="ai-response">
                <strong>🤖 Agent Suggested Response:</strong>
                <p>"{t.aiSuggestedResponse}"</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;