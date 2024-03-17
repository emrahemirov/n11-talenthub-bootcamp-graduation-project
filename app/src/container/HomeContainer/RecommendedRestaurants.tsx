import RestaurantItem from '@/components/RestaurantItem';
import { useFindRecommendedRestaurantsQuery } from '@/query/restaurant.query';
import { Stack, Text, Flex } from '@chakra-ui/react';
import { Spinner } from '@chakra-ui/react';

const RecommendedRestaurants = () => {
  const query = useFindRecommendedRestaurantsQuery();
  const restaurants = query.data?.data;

  if (query.isLoading)
    return (
      <Stack align={'center'} minH={'xs'}>
        <Spinner />
      </Stack>
    );

  return (
    <Stack spacing={4}>
      <Text fontWeight={'bold'} fontSize={'3xl'}>
        Önerilen Restoranlar
      </Text>
      <Flex
        pb={24}
        wrap={'wrap'}
        justify={'space-between'}
        rowGap={12}
        columnGap={16}
      >
        {restaurants?.map((restaurant) => {
          return <RestaurantItem key={restaurant.id} restaurant={restaurant} />;
        })}
      </Flex>
    </Stack>
  );
};

export default RecommendedRestaurants;
