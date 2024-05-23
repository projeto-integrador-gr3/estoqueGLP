// App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; 
import TransacaoPage from "./TransacaoPage";
import Cadastro from "./Cadastro"; 
import "./App.css";
import Login from "./Login";
import Signup from "./Signup";
import backgroundImage from "./Imagens/GLP.png";
import Header from "./Header";

function App() {
  return (
    <Router>
      {" "}
      {/* Envolve a aplicação com BrowserRouter */}
      <div
        className="App"
        style={{ backgroundImage: `url(${backgroundImage})` }}
      >
        <Header />

        <Routes>
        <Route path="/" element={<Login />} />
          <Route path="/cadastro" element={<Signup />} />
          <Route path="/admin" element={<Cadastro />} /> {/* Rota para a página de cadastro */}
          <Route path="/transacoes" element={<TransacaoPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
