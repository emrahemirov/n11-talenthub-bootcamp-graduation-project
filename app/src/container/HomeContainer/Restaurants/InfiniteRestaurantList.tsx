import { useFindAllRestaurantsInfiniteQuery } from '@/query/restaurant.query';
import { useCallback, useRef } from 'react';
import RestaurantItem from '@/components/RestaurantItem';
import { Flex, Stack, Text } from '@chakra-ui/react';

const InfiniteRestaurantList = () => {
  const { data, fetchNextPage, hasNextPage, isFetching, isLoading } =
    useFindAllRestaurantsInfiniteQuery();

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

  const restaurants = data?.pages.flatMap((val) => {
    return val.data.content;
  });

  return (
    <Stack spacing={4}>
      <Text fontWeight={'bold'} fontSize={'3xl'}>
        Restoranlar
      </Text>
      <Flex wrap={'wrap'} justify={'space-between'} rowGap={12} columnGap={16}>
        {restaurants?.map((restaurant) => {
          return (
            <div ref={lastElementRef} key={restaurant.id}>
              <RestaurantItem restaurant={restaurant} />
            </div>
          );
        })}
      </Flex>
    </Stack>
  );
};

export default InfiniteRestaurantList;
