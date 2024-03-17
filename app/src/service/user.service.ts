import { User } from '@/model/user.model';
import { QueryParams } from '@/model/query-params.model';
import { RestRequest } from '@/model/rest-request.model';
import { baseAxios } from '@/util/baseAxios';

export type FindAllUsersRequest = RestRequest<QueryParams>;
export type FindUserByIdRequest = RestRequest<null, Partial<{ id: number }>>;
export type UpdateUserRequest = RestRequest<
  null,
  null,
  {
    id: number;
    username: string;
    name: string;
    surname: string;
  }
>;
export type CreateUserRequest = RestRequest<
  null,
  null,
  {
    username: string;
    name: string;
    surname: string;
  }
>;
export type DeleteUserRequest = RestRequest<null, { id: number }>;

export const findUserById = ({ pathVariables }: FindUserByIdRequest) => {
  return baseAxios<User>({
    method: 'GET',
    url: `/users/${pathVariables?.id}`,
  });
};

export const createUser = ({ body }: CreateUserRequest) => {
  return baseAxios<User>({
    method: 'POST',
    url: '/users',
    data: body,
  });
};

export const updateUser = ({ body }: UpdateUserRequest) => {
  return baseAxios<User>({
    method: 'PUT',
    url: `/users/${body?.id}`,
    data: body,
  });
};

export const deleteUser = ({ pathVariables }: DeleteUserRequest) => {
  return baseAxios({
    method: 'DELETE',
    url: `/users/${pathVariables?.id}`,
  });
};
