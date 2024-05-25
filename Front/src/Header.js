// Header.js
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { BsMoon, BsSun } from "react-icons/bs";
import "./Header.css";
import { AiOutlineUser } from "react-icons/ai";
import { FaReceipt } from "react-icons/fa";
import { Button } from "react-bootstrap";
import { RiLogoutCircleRLine } from "react-icons/ri";
import { IoMdAddCircleOutline } from "react-icons/io";

function Header({ isLoggedIn, handleLogout }) {
  const [darkMode, setDarkMode] = useState(false);
  const navigate = useNavigate();

  const handleModeToggle = () => {
    setDarkMode(!darkMode);
    document.body.classList.toggle("dark-mode", darkMode);
  };

  const handleLogoutClick = () => {
    handleLogout();
    navigate("/");
  };

  return (
    <div className={`header ${darkMode ? "dark-mode" : ""}`}>
      <div>
        {isLoggedIn ? (
          <>
            <div className="header-item">
              <Link to="/transacoes" className="header-link">
                <FaReceipt className="header-icon" />
                <span className="header-text">Transação</span>
              </Link>
            </div>
            <div className="header-item">
              <Link to="/admin" className="header-link">
                <IoMdAddCircleOutline className="header-icon" />
                <span className="header-text">Produto / Fornecedor</span>
              </Link>
            </div>
            <div className="header-item" onClick={handleLogoutClick}>
              <RiLogoutCircleRLine className="header-icon" />
              <span className="header-text">Logout</span>
            </div>
          </>
        ) : (
          <>
            <div className="header-item">
              <Link to="/" className="header-link">
                <AiOutlineUser className="header-icon" />
                <span className="header-text">Login</span>
              </Link>
            </div>
            <div className="header-item">
              <Link to="/cadastro" className="header-link">
                <FaReceipt className="header-icon" />
                <span className="header-text">Cadastro</span>
              </Link>
            </div>
          </>
        )}
      </div>
      <div className="header-item">
        <Button variant="light" onClick={handleModeToggle}>
          {darkMode ? <BsSun /> : <BsMoon />}
        </Button>
      </div>
    </div>
  );
}

export default Header;
