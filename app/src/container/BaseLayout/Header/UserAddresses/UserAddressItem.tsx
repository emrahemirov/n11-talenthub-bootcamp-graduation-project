import { UserAddress } from '@/model/user-address.model';
import {
  Button,
  Card,
  CardBody,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Stack,
} from '@chakra-ui/react';
import MapModal from './MapModal';
import { useState } from 'react';
import { useUpdateUserAddressMutation } from '@/query/user-address.query';

type UserAddressItemProps = { address: UserAddress };

const UserAddressItem = ({ address }: UserAddressItemProps) => {
  const [center, setCenter] = useState({
    latitude: address.latitude,
    longitude: address.longitude,
  });
  const [addressLine, setAddressLine] = useState(address.addressLine);
  const [name, setName] = useState(address.name);

  const updateMutation = useUpdateUserAddressMutation();

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
            <Button onClick={handleUpdate}>Güncelle</Button>
          </Flex>
        </Stack>
      </CardBody>
    </Card>
  );
};

export default UserAddressItem;
