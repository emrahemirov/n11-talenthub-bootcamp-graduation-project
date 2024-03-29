import { BsStarFill } from 'react-icons/bs';
import { Restaurant } from '@/model/restaurant.model';
import { useFindReviewsByRestaurantIdInfiniteQuery } from '@/query/review.query';
import { useCallback, useRef } from 'react';
import {
  Button,
  Flex,
  Text,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Stack,
} from '@chakra-ui/react';
import UpdateReview from './UpdateReview';
import AddReview from './AddReview';

type RestaurantReviewListProps = { restaurant: Restaurant };

const RestaurantReviewList = ({ restaurant }: RestaurantReviewListProps) => {
  const { isOpen, onOpen, onClose } = useDisclosure();

  const { data, fetchNextPage, hasNextPage, isFetching, isLoading } =
    useFindReviewsByRestaurantIdInfiniteQuery({
      pathVariables: { restaurantId: restaurant.id },
      enabled: isOpen,
    });

  const observer = useRef<IntersectionObserver>();

  const lastElementRef = useCallback(
    (node: HTMLDivElement) => {
      if (isLoading) return;

      if (observer.current) observer.current.disconnect();

      observer.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && hasNextPage && !isFetching) {
          fetchNextPage();
        }
      });

      if (node) observer.current.observe(node);
    },
    [fetchNextPage, hasNextPage, isFetching, isLoading],
  );

  const reviews = data?.pages
    .flatMap((val) => {
      return val.data.content;
    })
    .reverse();

  if (isLoading) return <></>;

  return (
    <>
      <Button
        size={'xs'}
        onClick={onOpen}
        minW={28}
        color={'white'}
        alignSelf={'end'}
      >
        <Flex gap={2} align={'center'} color={'white'}>
          <BsStarFill />

          {restaurant.averageRate.toFixed(2)}
          <Text fontSize={'xs'}>({restaurant.totalReviewsCount})</Text>
        </Flex>
      </Button>
      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent maxW={'sm'}>
          <ModalHeader>Yorumlar</ModalHeader>
          <ModalCloseButton />
          <ModalBody maxH={'md'} overflow={'auto'}>
            <Stack spacing={4}>
              <AddReview restaurant={restaurant} />
              {reviews?.length ? (
                reviews.map((review) => {
                  return (
                    <div ref={lastElementRef} key={review.id}>
                      <UpdateReview review={review} />
                    </div>
                  );
                })
              ) : (
                <Text>Henüz yorum yok</Text>
              )}
            </Stack>
          </ModalBody>

          <ModalFooter></ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};

export default RestaurantReviewList;
