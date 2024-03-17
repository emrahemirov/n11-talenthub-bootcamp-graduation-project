import {
  DeleteUserAddressRequest,
  UpdateUserAddressRequest,
  deleteUserAddress,
  findUserAddressesByUserId,
  updateUserAddress,
} from '@/service/user-address.service';
import {
  CreateUserAddressRequest,
  createUserAddress,
} from '@/service/user-address.service';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { useFindUserByIdQuery } from './user.query';

export const USER_ADDRESS_QUERY_KEYS = {
  userAddresses: ['USER_ADDRESSES'],
} as const;

export const useFindUserAddressesByUserIdQuery = () => {
  const query = useFindUserByIdQuery();
  const user = query.data?.data;

  return useQuery({
    queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
    queryFn: () =>
      findUserAddressesByUserId({
        pathVariables: { userId: user?.id },
      }),
  });
};

export const useCreateUserAddressMutation = ({
  body,
}: CreateUserAddressRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => createUserAddress({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
      });
    },
  });
};

export const useUpdateUserAddressMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ body }: UpdateUserAddressRequest) =>
      updateUserAddress({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
      });
    },
  });
};

export const useDeleteUserAddressMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ pathVariables }: DeleteUserAddressRequest) =>
      deleteUserAddress({ pathVariables }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
      });
    },
  });
};
