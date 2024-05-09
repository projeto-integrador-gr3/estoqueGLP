// App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Importe BrowserRouter, Routes e Route
import TransacaoPage from "./TransacaoPage";
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
          {" "}
          {/* Envolve suas rotas com o componente Routes */}
          <Route path="/" element={<Login />} />{" "}
          {/* Define a rota para a página de login */}
          <Route path="/cadastro" element={<Signup />} />{" "}
          {/* Define a rota para a página de login */}
          <Route path="/transacoes" element={<TransacaoPage />} />{" "}
          {/* Adicione a rota para a página de transações */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
