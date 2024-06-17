import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; 
import "./perfil.css"
// Para react-router-dom versão 5
// Para react-router-dom versão 6 use `useNavigate`
// import { useNavigate } from 'react-router-dom';

const Profile = () => {
  const [userData, setUserData] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [editing, setEditing] = useState(false);
  const [editedData, setEditedData] = useState<any>(null);
  const [showConfirm, setShowConfirm] = useState(false);
  const [showSuccessPopup, setShowSuccessPopup] = useState(false); // Novo estado para o popup de sucesso
  const history = useNavigate(); // Para react-router-dom versão 5
  // const navigate = useNavigate(); // Para react-router-dom versão 6

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/usuarios/perfil', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setUserData(response.data);
        setLoading(false);
      } catch (error) {
        setError('Falha ao carregar os dados do perfil.');
        setLoading(false);
      }
    };

    fetchUserData();
  }, []);

  const handleEdit = () => {
    setEditing(true);
    setEditedData({ ...userData });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEditedData({
      ...editedData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSave = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.put('http://localhost:8080/usuarios/perfil', editedData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setUserData({ ...response.data });
      setEditing(false);
    } catch (error) {
      setError('Falha ao salvar as alterações.');
    }
  };

  const handleDelete = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.delete('http://localhost:8080/usuarios/perfil', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.status === 204) {
        // Sucesso - mostrar popup de confirmação
        setShowSuccessPopup(true);
        setTimeout(() => {
          localStorage.removeItem('token');
          setUserData(null);
          history('/login'); // Para react-router-dom versão 5
          // navigate('/login'); // Para react-router-dom versão 6
        }, 2000); // Tempo para exibir o popup antes de redirecionar
      } else {
        setError('Falha ao excluir a conta. Status: ' + response.status);
      }
    } catch (error) {
      console.error('Erro ao excluir a conta:', error);
      setError('Falha ao excluir a conta.');
    }
  };

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h1>Perfil do Usuário</h1>
      {!editing ? (
        <div>
          <p>Nome: {userData?.nome}</p>
          <p>Email: {userData?.email}</p>
          <p>Telefone: {userData?.telefone}</p>
          <p>Endereço: {userData?.endereco}</p>
          <button onClick={handleEdit}>Editar</button>
          <button style={{ backgroundColor: 'red', color: 'white' }} onClick={() => setShowConfirm(true)}>Excluir Conta</button>
        </div>
      ) : (
        <div>
          <input type="text" name="nome" value={editedData?.nome} onChange={handleChange} />
          <input type="text" name="email" value={editedData?.email} onChange={handleChange} />
          <input type="text" name="telefone" value={editedData?.telefone} onChange={handleChange} />
          <input type="text" name="endereco" value={editedData?.endereco} onChange={handleChange} />
          <button onClick={handleSave}>Salvar</button>
          <button style={{ backgroundColor: 'red', color: 'white' }} onClick={() => setShowConfirm(true)}>Excluir Conta</button>
        </div>
      )}
      {showConfirm && (
        <div className="popup">
          <div className="popup-content">
            <p>Deseja mesmo excluir sua conta?</p>
            <button onClick={handleDelete}>Sim</button>
            <button onClick={() => setShowConfirm(false)}>Cancelar</button>
          </div>
        </div>
      )}
      {showSuccessPopup && (
        <div className="popup">
          <div className="popup-content">
            <p>Conta excluída com sucesso!</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default Profile;
