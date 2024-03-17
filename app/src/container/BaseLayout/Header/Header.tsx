import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@radix-ui/react-popover';
import { Avatar, AvatarFallback, AvatarImage } from '@radix-ui/react-avatar';
import { FaRegUserCircle } from 'react-icons/fa';

const Header = () => {
  return (
    <header className='flex justify-between items-center p-4 w-full max-w-screen-xl mx-auto'>
      <img src='/talenthub_logo.png' width={314} height={35} />
      <Popover>
        <PopoverTrigger>
          <Avatar>
            <AvatarImage />
            <AvatarFallback>
              <FaRegUserCircle size={24} />
            </AvatarFallback>
          </Avatar>
        </PopoverTrigger>
        <PopoverContent className='p-2'></PopoverContent>
      </Popover>
    </header>
  );
};

export default Header;
