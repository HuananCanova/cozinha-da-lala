// src/pages/register/Register.tsx
import React, { useState } from 'react';
import axios from 'axios';
import './register.css';

const Register = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [nome, setNome] = useState('');
  const [telefone, setTelefone] = useState('');
  const [endereco, setEndereco] = useState('');
  const [error, setError] = useState('');

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/auth/register', {
        email,
        senha,
        nome,
        telefone,
        endereco
      });

      // Supondo que a resposta contenha um token e o nome do usuário
      const { name, token } = response.data;

      // Armazenar o token em localStorage ou cookies
      localStorage.setItem('token', token);
      localStorage.setItem('name', name);

      // Redirecionar ou atualizar o estado do usuário logado
      console.log(`Usuário ${name} registrado com sucesso!`);

      // Aqui você pode redirecionar o usuário para a página inicial ou outra página
      window.location.href = '/';

    } catch (error) {
      setError('Falha ao registrar. Verifique os campos e tente novamente.');
      console.error('Erro ao registrar:', error);
    }
  };

  return (
    <div className="register-container">
      <h1>Registro</h1>
      <form onSubmit={handleRegister}>
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
        <div className="form-group">
          <label>Nome:</label>
          <input
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Telefone:</label>
          <input
            type="text"
            value={telefone}
            onChange={(e) => setTelefone(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Endereço:</label>
          <input
            type="text"
            value={endereco}
            onChange={(e) => setEndereco(e.target.value)}
            required
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Register;
