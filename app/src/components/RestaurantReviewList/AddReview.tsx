import {
  Button,
  Card,
  CardBody,
  CardFooter,
  Stack,
  Textarea,
} from '@chakra-ui/react';
import { useState } from 'react';
import ReviewRate from './ReviewRate';
import { useCreateReviewMutation } from '@/query/review.query';
import { useFindUserByIdQuery } from '@/query/user.query';
import { Restaurant } from '@/model/restaurant.model';

type AddReviewProps = { restaurant: Restaurant };

const AddReview = ({ restaurant }: AddReviewProps) => {
  const [comment, setComment] = useState('');
  const [rate, setRate] = useState(0);
  const mutation = useCreateReviewMutation();
  const findUserByIdQuery = useFindUserByIdQuery();
  const user = findUserByIdQuery.data?.data;

  const handleAdd = () => {
    if (!user || !restaurant) return;

    mutation.mutate({
      body: { comment, rate, userId: user.id, restaurantId: restaurant.id },
    });
    setComment('');
    setRate(0);
  };

  return (
    <Card size={'sm'}>
      <CardBody>
        <Stack>
          <Textarea
            value={comment}
            onChange={(e) => {
              setComment(e.target.value);
            }}
          ></Textarea>
          <ReviewRate
            rate={rate}
            onRateChange={(rate) => {
              setRate(rate);
            }}
          />
        </Stack>
      </CardBody>
      <CardFooter>
        <Button isDisabled={rate === 0} onClick={handleAdd} w={'full'}>
          Yorum yap
        </Button>
      </CardFooter>
    </Card>
  );
};

export default AddReview;
