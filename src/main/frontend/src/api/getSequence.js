import axios from '../service/http';

export const getSequence = (index) => {
  return axios.get(`/alticci/${index}`).then((response) => response.data);
};
