import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; // Importe o Link
import './login.css';

export const Login = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        email,
        senha
      });

      // Supondo que a resposta contenha um token e o nome do usuário
      const { name, token, role } = response.data;

      // Armazenar o token em localStorage ou cookies
      localStorage.setItem('token', token);
      localStorage.setItem('name', name);
      localStorage.setItem('role', role);


      // Redirecionar ou atualizar o estado do usuário logado
      console.log(`Usuário ${name} logado com sucesso!`);

      // Aqui você pode redirecionar o usuário para a página inicial ou outra página
      window.location.href = '/';

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
      <Link to="/register">Não tem uma conta? Cadastre-se aqui.</Link>
    </div>
  );
};
