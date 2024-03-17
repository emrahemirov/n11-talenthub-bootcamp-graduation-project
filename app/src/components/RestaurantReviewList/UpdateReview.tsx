import { Review } from '@/model/review.model';
import { useFindUserByIdQuery } from '@/query/user.query';
import { colors } from '@/styles/theme';
import {
  Box,
  Card,
  CardBody,
  Flex,
  IconButton,
  Stack,
  Text,
  Textarea,
} from '@chakra-ui/react';
import { BsStarFill } from 'react-icons/bs';
import { BiEdit } from 'react-icons/bi';
import { TiTick } from 'react-icons/ti';
import { FaRegTrashCan } from 'react-icons/fa6';

import { useState } from 'react';
import {
  useDeleteReviewMutation,
  useUpdateReviewMutation,
} from '@/query/review.query';
import ReviewRate from './ReviewRate';

type UpdateReviewProps = { review: Review };

const UpdateReview = ({ review }: UpdateReviewProps) => {
  const [newRate, setNewRate] = useState(review.rate);
  const [newComment, setNewComment] = useState(review.comment);
  const [isEditing, setIsEditing] = useState(false);
  const findUserByIdQuery = useFindUserByIdQuery();
  const updateMutation = useUpdateReviewMutation();
  const deleteMutation = useDeleteReviewMutation();
  const user = findUserByIdQuery.data?.data;

  const isCurrentUser = review.user.id === user?.id;

  const handleUpdate = () => {
    updateMutation.mutate({
      body: { ...review, comment: newComment, rate: newRate },
    });
    setIsEditing(false);
  };

  return (
    <Card
      size={'sm'}
      border={isCurrentUser ? `1px solid ${colors.n11[500]}` : ''}
    >
      <CardBody>
        <Stack>
          <Flex justify={'space-between'}>
            {isEditing ? (
              <Textarea
                rows={4}
                onChange={(e) => {
                  setNewComment(e.target.value);
                }}
                variant={'filled'}
                defaultValue={review.comment}
              />
            ) : (
              <Text my={2}>{review.comment}</Text>
            )}
            <Flex>
              {isCurrentUser ? (
                <>
                  {isEditing ? (
                    <IconButton
                      onClick={handleUpdate}
                      rounded={'full'}
                      variant={'ghost'}
                      aria-label='edit'
                      icon={<TiTick />}
                    />
                  ) : (
                    <IconButton
                      onClick={() => {
                        setIsEditing(true);
                      }}
                      rounded={'full'}
                      variant={'ghost'}
                      aria-label='edit'
                      icon={<BiEdit />}
                    />
                  )}
                  <IconButton
                    onClick={() => {
                      deleteMutation.mutate({
                        pathVariables: { id: review.id },
                      });
                    }}
                    colorScheme='red'
                    rounded={'full'}
                    variant={'ghost'}
                    aria-label='edit'
                    icon={<FaRegTrashCan />}
                  />
                </>
              ) : null}
            </Flex>
          </Flex>
          <Flex justify={'space-between'}>
            <Text fontSize={'xs'}>
              {review.user.name} {review.user.surname}
            </Text>

            <Flex gap={1} align={'center'}>
              {isEditing ? (
                <ReviewRate
                  rate={review.rate}
                  onRateChange={(rate) => {
                    setNewRate(rate);
                  }}
                />
              ) : (
                <Box as={BsStarFill} color={'n11-secondary.500'} />
              )}
              {review.rate}
            </Flex>
          </Flex>
        </Stack>
      </CardBody>
    </Card>
  );
};

export default UpdateReview;
