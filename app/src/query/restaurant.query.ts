import {
  findAllRestaurants,
  findRecommendedRestaurants,
} from '@/service/restaurant.service';
import { useInfiniteQuery, useQuery } from '@tanstack/react-query';
import { useFindUserByIdQuery } from './user.query';

export const RESTAURANT_QUERY_KEYS = {
  restaurants: ['RESTAURANTS'],
  recommendedRestaurants: ['RECOMMENDED_RESTAURANTS'],
} as const;

export const useFindAllRestaurantsInfiniteQuery = () => {
  return useInfiniteQuery({
    queryKey: RESTAURANT_QUERY_KEYS.restaurants,
    queryFn: ({ pageParam }) =>
      findAllRestaurants({ requestParams: { page: pageParam, size: 20 } }),
    initialPageParam: 0,
    getNextPageParam: (lastPage, pages) =>
      lastPage.data.hasNext ? pages.length : null,
  });
};

export const useFindRecommendedRestaurantsQuery = () => {
  const query = useFindUserByIdQuery();
  const user = query.data?.data;

  return useQuery({
    queryKey: RESTAURANT_QUERY_KEYS.recommendedRestaurants,
    queryFn: () =>
      findRecommendedRestaurants({ requestParams: { userId: user?.id } }),
  });
};
