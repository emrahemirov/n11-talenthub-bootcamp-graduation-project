import { Stack } from '@chakra-ui/react';
import InfiniteRestaurantList from './Restaurants/InfiniteRestaurantList';

const HomeContainer = () => {
  return (
    <Stack spacing={4}>
      <InfiniteRestaurantList />
    </Stack>
  );
};

export default HomeContainer;
