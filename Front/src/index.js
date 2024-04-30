import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css'; 
import App from './App';
import Cadastro from './Cadastro';
import Login from './Login';
import reportWebVitals from './reportWebVitals';

function Index() {
  const [page, setPage] = useState('home');

  const renderPage = () => {
    switch (page) {
      case 'home':
        return <App />;
      case 'cadastro':
        return <Cadastro />;
      case 'login':
        return <Login />;
      default:
        return <App />;
    }
  };

  return (
    <div className="bg-dark text-light"> 
      
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark"> 
        <div className="container-fluid">
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <button
                  className="nav-link btn btn-link"
                  onClick={() => setPage('home')}
                >
                  Home
                </button>
              </li>
              <li className="nav-item">
                <button
                  className="nav-link btn btn-link"
                  onClick={() => setPage('cadastro')}
                >
                  Cadastro
                </button>
              </li>
              <li className="nav-item">
                <button
                  className="nav-link btn btn-link"
                  onClick={() => setPage('login')}
                >
                  Login
                </button>
              </li>
            </ul>
          </div>
        </div>
      </nav>

     
      {renderPage()}
    </div>
  );
}

ReactDOM.render(
  <React.StrictMode>
    <Index />
  </React.StrictMode>,
  document.getElementById('root')
);

reportWebVitals();
