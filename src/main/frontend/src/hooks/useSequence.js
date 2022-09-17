import { useEffect, useState } from 'react';
import { getSequence } from '../api/getSequence';

export const useSequence = (sequenceIndex) => {
  const [sequence, setSequence] = useState(null);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const fetchSequence = async () => {
    setIsLoading(true);
    try {
      const response = await getSequence(sequenceIndex);
      setSequence(response);
    } catch (err) {
      setError(err);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    setSequence(null);
  }, [sequenceIndex]);

  return { sequence, isLoading, error, fetchSequence };
};
