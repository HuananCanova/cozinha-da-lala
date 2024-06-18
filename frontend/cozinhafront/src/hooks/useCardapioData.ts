import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import { CardapioData } from '../interface/CardapioData';

const API_URL = 'http://localhost:8080';

const fetchData = async () => {
  try {
    const response = await axios.get<CardapioData[]>(`${API_URL}/cardapios`);
    console.log('API Response:', response.data); // Adicione este log
    return response.data;
  } catch (error) {
    console.error('Error fetching data:', error); // Adicione tratamento de erro
    throw error;
  }
};

export function useCardapioData() {
  const query = useQuery<CardapioData[], Error>({
    queryFn: fetchData,
    queryKey: ['cardapio-data'],
    retry: 2
  });

  console.log('Query Data:', query.data); // Adicione este log

  return query;
}
