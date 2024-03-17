import {
  Container,
  Flex,
  Image,
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverHeader,
  PopoverBody,
  PopoverArrow,
  PopoverCloseButton,
  Avatar,
} from '@chakra-ui/react';

const Header = () => {
  return (
    <Container py={4} maxW={'6xl'} as={'header'}>
      <Flex justify={'space-between'} align={'center'}>
        <Image src='/talenthub_logo.png' width={314} height={35} />
        <Popover>
          <PopoverTrigger>
            <Avatar size={'sm'} />
          </PopoverTrigger>
          <PopoverContent>
            <PopoverArrow />
            <PopoverCloseButton />
            <PopoverHeader>Confirmation!</PopoverHeader>
            <PopoverBody>
              Are you sure you want to have that milkshake?
            </PopoverBody>
          </PopoverContent>
        </Popover>
      </Flex>
    </Container>
  );
};

export default Header;
