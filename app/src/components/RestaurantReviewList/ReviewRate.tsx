import { Box, Flex } from '@chakra-ui/react';
import { useState } from 'react';
import { BsStarFill, BsStar } from 'react-icons/bs';

type ReviewRateProps = { rate: number; onRateChange: (rate: number) => void };

const ReviewRate = ({ rate, onRateChange }: ReviewRateProps) => {
  const [newRate, setNewRate] = useState(rate);

  return (
    <Flex gap={1}>
      {new Array(5).fill(null).map((_, index) => {
        return (
          <Box
            key={index}
            onClick={() => {
              setNewRate(index + 1);
              onRateChange(index + 1);
            }}
            as={index + 1 <= newRate ? BsStarFill : BsStar}
            color={'n11-secondary.500'}
          />
        );
      })}
    </Flex>
  );
};

export default ReviewRate;
