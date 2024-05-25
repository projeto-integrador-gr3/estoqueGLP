// App.js
import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from "react-router-dom"; 
import TransacaoPage from "./TransacaoPage";
import Cadastro from "./Cadastro"; 
import "./App.css";
import Login from "./Login";
import Signup from "./Signup";
import backgroundImage from "./Imagens/GLP.png";
import Header from "./Header";

function RequireAuth({ children }) {
  const location = useLocation();
  const isAuthenticated = localStorage.getItem('token') !== null;

  if (!isAuthenticated) {
    return <Navigate to="/" state={{ from: location }} />;
  }

  return children;
}

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    setIsAuthenticated(token !== null);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('isAdmin');
    setIsAuthenticated(false);
  };

  return (
    <Router>
      <div className="App" style={{ backgroundImage: `url(${backgroundImage})` }}>
        <Header isLoggedIn={isAuthenticated} handleLogout={handleLogout} />

        <Routes>
          <Route path="/" element={<Login setIsAuthenticated={setIsAuthenticated} />} />
          <Route path="/cadastro" element={<Signup />} />
          <Route path="/admin" element={
            <RequireAuth>
              <Cadastro />
            </RequireAuth>
          } />
          <Route path="/transacoes" element={
            <RequireAuth>
              <TransacaoPage />
            </RequireAuth>
          } />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
