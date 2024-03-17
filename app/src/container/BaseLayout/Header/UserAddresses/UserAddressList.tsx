import { useFindUserAddressesByUserIdQuery } from '@/query/user-address.query';
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Button,
  useDisclosure,
  Stack,
} from '@chakra-ui/react';
import React from 'react';
import UserAddressItem from './UserAddressItem';

const UserAddressList = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const query = useFindUserAddressesByUserIdQuery();
  const addresses = query.data?.data;

  return (
    <>
      <Button w={'full'} onClick={onOpen} colorScheme='gray'>
        Adreslerim
      </Button>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Adreslerim</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <Stack>
              {addresses?.map((address) => {
                return <UserAddressItem key={address.id} address={address} />;
              })}
            </Stack>
          </ModalBody>

          <ModalFooter></ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};

export default UserAddressList;
