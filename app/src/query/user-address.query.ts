import {
  DeleteUserAddressRequest,
  FindUserAddressesByUserIdRequest,
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

export const USER_ADDRESS_QUERY_KEYS = {
  userAddresses: ['USER_ADDRESSES'],
} as const;

export const useFindUserAddressesByUserIdQuery = ({
  pathVariables,
}: FindUserAddressesByUserIdRequest) => {
  return useQuery({
    queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
    queryFn: () =>
      findUserAddressesByUserId({
        pathVariables,
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

export const useUpdateUserAddressMutation = ({
  body,
}: UpdateUserAddressRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => updateUserAddress({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
      });
    },
  });
};

export const useDeleteUserAddressMutation = ({
  pathVariables,
}: DeleteUserAddressRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => deleteUserAddress({ pathVariables }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_ADDRESS_QUERY_KEYS.userAddresses,
      });
    },
  });
};
