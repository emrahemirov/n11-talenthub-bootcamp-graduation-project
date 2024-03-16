import { RestRequest } from '@/model/rest-request.model';
import { baseAxios } from '@/util/baseAxios';
import { UserAddress } from '@/model/user-address.model';

export type FindUserAddressesByUserIdRequest = RestRequest<
  null,
  Partial<{ userId: number }>
>;

export type UpdateUserAddressRequest = RestRequest<
  null,
  null,
  Omit<UserAddress, 'createdAt' | 'updatedAt'>
>;
export type CreateUserAddressRequest = RestRequest<
  null,
  null,
  Omit<UserAddress, 'createdAt' | 'updatedAt'>
>;
export type DeleteUserAddressRequest = RestRequest<null, { id: number }>;
export type SetPreferredUserAddress = RestRequest<
  { userId: number },
  { id: number }
>;

export const findUserAddressesByUserId = async ({
  pathVariables,
}: FindUserAddressesByUserIdRequest) => {
  return baseAxios<UserAddress[]>({
    method: 'GET',
    url: `/user-addresses/by-user-id/${pathVariables?.userId}`,
  });
};

export const createUserAddress = async ({ body }: CreateUserAddressRequest) => {
  return baseAxios<UserAddress>({
    method: 'POST',
    url: '/user-addresses',
    data: body,
  });
};

export const setPreferredUserAddress = async ({
  requestParams,
  pathVariables,
}: SetPreferredUserAddress) => {
  return baseAxios({
    method: 'PATCH',
    url: `/user-addresses/${pathVariables?.id}/preferred`,
    params: requestParams,
  });
};

export const updateUserAddress = async ({ body }: UpdateUserAddressRequest) => {
  return baseAxios<UserAddress>({
    method: 'PUT',
    url: `/user-addresses/${body?.id}`,
    data: body,
  });
};

export const deleteUserAddress = async ({
  pathVariables,
}: DeleteUserAddressRequest) => {
  return baseAxios({
    method: 'DELETE',
    url: `/user-addresses/${pathVariables?.id}`,
  });
};
