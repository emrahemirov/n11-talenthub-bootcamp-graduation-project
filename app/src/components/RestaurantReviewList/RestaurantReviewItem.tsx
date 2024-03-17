import { Review } from '@/model/review.model';
import { useFindUserByIdQuery } from '@/query/user.query';
import { colors } from '@/styles/theme';
import { Box, Card, CardBody, Flex, Stack, Text } from '@chakra-ui/react';
import { BsStarFill } from 'react-icons/bs';

type RestaurantReviewItemProps = { review: Review };

const RestaurantReviewItem = ({ review }: RestaurantReviewItemProps) => {
  const query = useFindUserByIdQuery();
  const user = query.data?.data;

  const isCurrentUser = review.user.id === user?.id;

  return (
    <Card
      size={'sm'}
      bgColor={isCurrentUser ? 'n11.50' : ''}
      border={isCurrentUser ? `1px solid ${colors.n11[500]}` : ''}
    >
      <CardBody>
        <Stack>
          <Text>{review.comment}</Text>
          <Flex justify={'space-between'}>
            <Text fontSize={'xs'}>
              {review.user.name} {review.user.surname}
            </Text>
            <Flex gap={1} align={'center'}>
              <Box as={BsStarFill} color={'n11.500'} />
              {review.rate}
            </Flex>
          </Flex>
        </Stack>
      </CardBody>
    </Card>
  );
};

export default RestaurantReviewItem;
