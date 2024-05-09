import React, { useState } from "react";
import { Link } from "react-router-dom";
import { BsMoon, BsSun } from "react-icons/bs";
import "./Header.css";
import { AiOutlineUser } from "react-icons/ai";
import { FaReceipt } from "react-icons/fa";
import { Button } from "react-bootstrap";


function Header() {
  const [darkMode, setDarkMode] = useState(false);

  const handleModeToggle = () => {
    setDarkMode(!darkMode);

    document.body.classList.toggle("dark-mode", darkMode);
  };

  return (
    <div className={`header ${darkMode ? "dark-mode" : ""}`}>
      <div>
        <div className="header-item">
          <Link to="/login" className="header-link">
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
