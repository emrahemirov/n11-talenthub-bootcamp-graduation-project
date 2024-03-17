import { UserAddress } from '@/model/user-address.model';
import {
  Button,
  Card,
  CardBody,
  Flex,
  FormControl,
  FormLabel,
  IconButton,
  Input,
  Stack,
} from '@chakra-ui/react';
import MapModal from './MapModal';
import { useState } from 'react';
import {
  useDeleteUserAddressMutation,
  useUpdateUserAddressMutation,
} from '@/query/user-address.query';
import { BiTrash } from 'react-icons/bi';

type UserAddressItemProps = { address: UserAddress };

const UserAddressItem = ({ address }: UserAddressItemProps) => {
  const [center, setCenter] = useState({
    latitude: address.latitude,
    longitude: address.longitude,
  });
  const [addressLine, setAddressLine] = useState(address.addressLine);
  const [name, setName] = useState(address.name);

  const updateMutation = useUpdateUserAddressMutation();
  const deleteMutation = useDeleteUserAddressMutation();

  const handleUpdate = () => {
    updateMutation.mutate({
      body: { ...address, addressLine, name, ...center },
    });
  };

  return (
    <Card bgColor={address.isPreferred ? 'n11.100' : ''}>
      <CardBody>
        <Stack align={'center'} justify={'space-between'}>
          <FormControl>
            <FormLabel>Adres</FormLabel>
            <Input
              defaultValue={address.addressLine}
              onChange={(e) => {
                setAddressLine(e.target.value);
              }}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Kayıt adı</FormLabel>
            <Input
              defaultValue={address.name}
              onChange={(e) => {
                setName(e.target.value);
              }}
            />
          </FormControl>

          <Flex justify={'space-between'} w={'full'}>
            <MapModal
              initialCenter={center}
              onCenterChange={(newCenter) => {
                setCenter(newCenter);
              }}
            />
            <Flex gap={2}>
              <IconButton
                colorScheme='red'
                aria-label='delete'
                icon={<BiTrash />}
                onClick={() => {
                  deleteMutation.mutate({ pathVariables: { id: address.id } });
                }}
              />
              <Button onClick={handleUpdate}>Güncelle</Button>
            </Flex>
          </Flex>
        </Stack>
      </CardBody>
    </Card>
  );
};

export default UserAddressItem;
