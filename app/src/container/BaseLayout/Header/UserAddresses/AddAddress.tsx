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
import { useCreateUserAddressMutation } from '@/query/user-address.query';

const AddAddress = () => {
  const [center, setCenter] = useState({
    latitude: '38.430623',
    longitude: '27.140300',
  });
  const [addressLine, setAddressLine] = useState('');
  const [name, setName] = useState('');

  const createMutation = useCreateUserAddressMutation();

  const handleCreate = () => {
    createMutation.mutate({
      body: { addressLine, name, ...center },
    });
    setName('');
    setAddressLine('');
  };

  return (
    <Card>
      <CardBody>
        <Stack align={'center'} justify={'space-between'}>
          <FormControl>
            <FormLabel>Adres</FormLabel>
            <Input
              value={addressLine}
              onChange={(e) => {
                setAddressLine(e.target.value);
              }}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Kayıt adı</FormLabel>
            <Input
              value={name}
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
            <Button onClick={handleCreate}>Ekle</Button>
          </Flex>
        </Stack>
      </CardBody>
    </Card>
  );
};

export default AddAddress;
