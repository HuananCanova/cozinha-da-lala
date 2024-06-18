import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './editarCardapio.css';

const EditarCardapio = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const [descricao, setDescricao] = useState('');
  const [preco, setPreco] = useState('');
  const [data, setData] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchCardapio = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/cardapios/${id}`);
        const cardapio = response.data;
        console.log('Cardápio recebido:', cardapio); // Adicionado para debug
        setDescricao(cardapio.descricao);
        setPreco(String(cardapio.preco));
        const formattedDate = new Date(cardapio.data).toISOString().split('T')[0]; // Formatação da data
        setData(formattedDate);
      } catch (error) {
        console.error('Failed to fetch cardapio:', error);
      }
    };

    fetchCardapio();
  }, [id]);

  const handleUpdate = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      await axios.put(`http://localhost:8080/cardapios/${id}`, {
        descricao,
        preco: Number(preco),
        data,
      });

      console.log('Cardápio atualizado com sucesso!');
      navigate('/');
    } catch (error) {
      setError('Falha ao atualizar cardápio. Verifique os campos e tente novamente.');
      console.error('Erro ao atualizar cardápio:', error);
    }
  };

  return (
    <div className="edit-cardapio-container">
      <h1>Editar Cardápio</h1>
      <form onSubmit={handleUpdate}>
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
        <button type="submit">Salvar</button>
      </form>
    </div>
  );
};

export default EditarCardapio;
