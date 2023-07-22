import { Box, Button, Flex, Text } from '@chakra-ui/react';
import React from 'react';
import { NavLink } from 'react-router-dom';


function NavBar() {
    return (
        <Flex as="nav" alignItems="center" justify="space-between" h="10vh" w="100%" backgroundColor="#050A30">
            {/* Logo */}
            <NavLink to="/">
                <Text fontWeight="medium" color="white" fontSize="lg" ml="24px">Commune</Text>
            </NavLink>
            <Flex gap="24px" mr="40px" h="10vh" alignItems="center">
                {/* Home */}
                <Box>
                    <NavLink to='/'>
                        <Text fontWeight="medium" color="white" fontSize="lg">Home</Text>
                    </NavLink>
                </Box>
                {/* Features */}
                <Box>
                    <NavLink to='/'>
                        <Text fontWeight="medium" color="white" fontSize="lg">Community</Text>
                    </NavLink>
                </Box>
                {/* About Us */}
                <Box>
                    <NavLink to='/'>
                        <Text fontWeight="medium" color="white" fontSize="lg">Events</Text>
                    </NavLink>
                </Box>
                {/* News */}
                <Box>
                    <NavLink to='/'>
                        <Text fontWeight="medium" color="white" fontSize="lg">FAQs</Text>
                    </NavLink>
                </Box>
                {/* Contact */}
                <Box>
                    <NavLink to='/'>
                        <Text fontWeight="medium" color="white" fontSize="lg">Contact Us</Text>
                    </NavLink>
                </Box>
                {/* CTA */}
                <NavLink to='/'>
                    <Button fontWeight="medium" colorScheme="teal" variant="solid" fontSize="lg" mb="8px">Sign In/Up</Button>
                </NavLink>
            </Flex>
        </Flex>
    );
}

export default NavBar;