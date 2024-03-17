import { Stack } from '@chakra-ui/react';
import InfiniteRestaurantList from './InfiniteRestaurantList';
import RecommendedRestaurants from './RecommendedRestaurants';

const HomeContainer = () => {
  return (
    <Stack spacing={4}>
      <RecommendedRestaurants />
      <InfiniteRestaurantList />
    </Stack>
  );
};

export default HomeContainer;
