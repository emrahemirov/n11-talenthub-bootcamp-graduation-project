import {
  findAllRestaurants,
  findRecommendedRestaurants,
} from '@/service/restaurant.service';
import { useAuthStore } from '@/store/auth.store';
import { useInfiniteQuery, useQuery } from '@tanstack/react-query';

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
  const { user } = useAuthStore();

  return useQuery({
    queryKey: RESTAURANT_QUERY_KEYS.recommendedRestaurants,
    queryFn: () =>
      findRecommendedRestaurants({ requestParams: { userId: user?.id } }),
  });
};
