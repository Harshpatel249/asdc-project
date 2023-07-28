import React from 'react';
import { Box, Button, Flex, Text, Menu, MenuButton, MenuList, MenuItem } from '@chakra-ui/react';
import { NavLink } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

function NavBar() {
    const bearerToken = localStorage.getItem('BearerToken');
    const navigate = useNavigate();

    const handleCreateCommunity = () => {
        // Add your navigation logic for "Create Community" here
    };

    const handleMyCommunities = () => {
        // Add your navigation logic for "My Community" here
    };

    const handleAllCommunities = () => {
        // Add your navigation logic for "All Communities" here
    };

    const handleCreateEvent = () => {
        // Add your navigation logic for "Create Community" here
    };

    const handleMyEvents = () => {
        // Add your navigation logic for "My Community" here
    };

    const handleAllEvents = () => {
        // Add your navigation logic for "All Communities" here
    };

    const handleLogout = () => {
        localStorage.removeItem('BearerToken');
        localStorage.removeItem('userID');
        localStorage.removeItem('fullName');

        navigate('/');
    };

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
                {bearerToken && (
                    <Menu>
                        {/* Community */}
                        <MenuButton fontWeight="medium" color="white" fontSize="lg">
                        <Text fontWeight="medium" color="white" fontSize="lg">Community</Text>
                        </MenuButton>
                        <MenuList backgroundColor="#050A30">
                            <MenuItem onClick={handleCreateCommunity}>
                                Create Community
                            </MenuItem>
                            <MenuItem onClick={handleMyCommunities}>
                                My Communities
                            </MenuItem>
                            <MenuItem onClick={handleAllCommunities}>
                                All Communities
                            </MenuItem>
                        </MenuList>
                    </Menu>
                )}
                {bearerToken && (
                    <Menu>
                        {/* Events */}
                        <MenuButton fontWeight="medium" color="white" fontSize="lg">
                        <Text fontWeight="medium" color="white" fontSize="lg">Events</Text>
                        </MenuButton>
                        <MenuList backgroundColor="#050A30">
                            <MenuItem onClick={handleCreateEvent}>
                                Create Event
                            </MenuItem>
                            <MenuItem onClick={handleMyEvents}>
                                My Events
                            </MenuItem>
                            <MenuItem onClick={handleAllEvents}>
                                All Events
                            </MenuItem>
                        </MenuList>
                    </Menu>
                )}
                {/* FAQs */}
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
                {/* Logout */}
                {bearerToken ? (
                    <Box>
                     <NavLink>
                        <Button fontWeight="medium" colorScheme="teal" variant="solid" fontSize="lg" mb="8px" onClick={handleLogout}>Logout</Button>
                    </NavLink>
                    </Box>
                ) : (
                    <NavLink to='/login'>
                        <Button fontWeight="medium" colorScheme="teal" variant="solid" fontSize="lg" mb="8px">Log In</Button>
                    </NavLink>
                )}
            </Flex>
        </Flex>
    );
}

export default NavBar;
