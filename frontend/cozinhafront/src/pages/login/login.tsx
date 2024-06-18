import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // Usando useNavigate para redirecionar

import './login.css';

export const Login = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        email,
        senha
      });

      // Supondo que a resposta contenha um token, nome e role do usuário
      const { name, token, role } = response.data;

      // Armazenar o token, nome e role no localStorage
      localStorage.setItem('token', token);
      localStorage.setItem('name', name);
      localStorage.setItem('role', role);

      // Redirecionar o usuário para a página inicial
      navigate('/');

    } catch (error) {
      setError('Falha ao fazer login. Verifique suas credenciais.');
      console.error('Erro ao fazer login:', error);
    }
  };

  return (
    <div className="login-container">
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Senha:</label>
          <input
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            required
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit">Login</button>
      </form>
      {/* Adicione um link para o formulário de cadastro */}
      <a href="/register">Não tem uma conta? Cadastre-se aqui.</a>
    </div>
  );
};
