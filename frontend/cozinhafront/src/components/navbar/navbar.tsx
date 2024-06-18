import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './navbar.css';

export const Navbar = () => {
  const name = localStorage.getItem('name');
  const role = localStorage.getItem('role');
  const navigate = useNavigate();
  const [showDropdown, setShowDropdown] = useState(false);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('name');
    localStorage.removeItem('role');
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-logo">
        Cozinha da Lala
      </div>
      <ul className="navbar-links">
      <div className="navbar-center">
        <Link to="/">Ver Cardápios</Link>
      </div>
        <li>
          {name ? (
            <div className="dropdown">
              <span 
                onClick={() => setShowDropdown(!showDropdown)}
                className="dropdown-toggle"
              >
                {name}
              </span>
              {showDropdown && (
                <div className="dropdown-menu">
                  {role === 'ADMIN' ? (
                    <>
                      <Link to="/register" className="dropdown-item">Cadastrar Cliente</Link>
                      <Link to="/cardapio" className="dropdown-item">Cadastrar Cardápio</Link>
                      <Link to="/generate-report" className="dropdown-item">Gerar Relatório</Link>
                    </>
                  ) : (
                    <>
                      <Link to="/perfil" className="dropdown-item">Perfil</Link>
                      <Link to="/meus-pedidos" className="dropdown-item">Meus Pedidos</Link>
                    </>
                  )}
                  <span onClick={handleLogout} className="dropdown-item">Sair</span>
                </div>
              )}
            </div>
          ) : (
            <Link to="/login">Login</Link>
          )}
        </li>
      </ul>
    </nav>
  );
};
