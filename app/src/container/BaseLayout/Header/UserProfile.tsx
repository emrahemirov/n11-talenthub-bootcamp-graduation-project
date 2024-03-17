import {
  useFindUserByIdQuery,
  useUpdateUserMutation,
} from '@/query/user.query';
import {
  Avatar,
  Popover,
  PopoverHeader,
  PopoverBody,
  PopoverArrow,
  PopoverCloseButton,
  PopoverContent,
  PopoverTrigger,
  Stack,
  Input,
  FormControl,
  FormLabel,
  Button,
  PopoverFooter,
} from '@chakra-ui/react';
import { useState } from 'react';
import UserAddressList from './UserAddresses/UserAddressList';

const UserProfile = () => {
  const [username, setUsername] = useState('');
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const query = useFindUserByIdQuery();
  const user = query.data?.data;

  const updateMutation = useUpdateUserMutation();

  const handleEdit = () => {
    if (!user) return;

    updateMutation.mutate({
      body: {
        id: user.id,
        username: username || user.username,
        name: name || user.name,
        surname: surname || user.surname,
      },
    });
  };

  if (query.isLoading) return <></>;

  return (
    <Popover>
      <PopoverTrigger>
        <Avatar size={'sm'} />
      </PopoverTrigger>
      <PopoverContent>
        <PopoverArrow />
        <PopoverCloseButton />
        <PopoverHeader>Kullanıcı Bilgileri</PopoverHeader>
        <PopoverBody>
          <Stack>
            <FormControl>
              <FormLabel>Kullanıcı adı</FormLabel>
              <Input
                defaultValue={user?.username}
                onChange={(e) => {
                  setUsername(e.target.value);
                }}
              />
            </FormControl>
            <FormControl>
              <FormLabel>Ad</FormLabel>
              <Input
                defaultValue={user?.name}
                onChange={(e) => {
                  setName(e.target.value);
                }}
              />
            </FormControl>
            <FormControl>
              <FormLabel>Soyad</FormLabel>
              <Input
                defaultValue={user?.surname}
                onChange={(e) => {
                  setSurname(e.target.value);
                }}
              />
            </FormControl>

            <Button w={'full'} onClick={handleEdit}>
              Güncelle
            </Button>
          </Stack>
        </PopoverBody>
        <PopoverFooter>
          <UserAddressList />
        </PopoverFooter>
      </PopoverContent>
    </Popover>
  );
};

export default UserProfile;
