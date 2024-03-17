import { Container, Flex, Image } from '@chakra-ui/react';
import UserProfile from './UserProfile';

const Header = () => {
  return (
    <Container py={4} maxW={'6xl'} as={'header'}>
      <Flex justify={'space-between'} align={'center'}>
        <Image src='/talenthub_logo.png' width={314} height={35} />
        <UserProfile />
      </Flex>
    </Container>
  );
};

export default Header;
