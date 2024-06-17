// src/pages/register/CardapioRegister.tsx
import React, { useState } from 'react';
import axios from 'axios';
import './cardapio.css';

const Cardapio = () => {
  const [descricao, setDescricao] = useState('');
  const [preco, setPreco] = useState('');
  const [data, setData] = useState('');
  const [error, setError] = useState('');

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/cardapios', {
        descricao,
        preco: Number(preco),
        data,
      });

      console.log('Cardápio cadastrado com sucesso!');

      // Aqui você pode redirecionar o usuário para a página inicial ou outra página
      window.location.href = '/';

    } catch (error) {
      setError('Falha ao cadastrar cardápio. Verifique os campos e tente novamente.');
      console.error('Erro ao cadastrar cardápio:', error);
    }
  };

  return (
    <div className="register-container">
      <h1>Cadastro de Cardápio</h1>
      <form onSubmit={handleRegister}>
        <div className="form-group">
          <label>Descrição:</label>
          <input
            type="text"
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Preço:</label>
          <input
            type="number"
            value={preco}
            onChange={(e) => setPreco(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Data:</label>
          <input
            type="date"
            value={data}
            onChange={(e) => setData(e.target.value)}
            required
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit">Cadastrar Cardápio</button>
      </form>
    </div>
  );
};

export default Cardapio;
