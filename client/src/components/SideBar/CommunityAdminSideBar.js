import { Flex, List, ListItem, Box } from '@chakra-ui/react';
import React from 'react';
import { NavLink, useParams } from 'react-router-dom';

function CommunityAdminSideBar(props) {
    const choice = props.choice;
    let { cid } = useParams();
    return (
        <Flex borderRadius="10px" bg="#000080">
            <List color="white" fontSize="1em" fontWeight="medium" spacing={4} mt="24px">
                <ListItem>
                    <NavLink to={`/community/${cid}/admin`}>
                        {choice === 1?
                        <Box w="150px" align="center" mr="16px" ml="16px" pt="4px" pb="4px" bg="#050A30" borderRadius="10px">
                            General
                        </Box>:
                        <Box w="150px" align="center" m="4px">
                            General
                        </Box>}
                    </NavLink>
                </ListItem>
                <ListItem>
                    <NavLink to={`/community/${cid}/admin/manage-members`}>
                    {choice === 2?
                        <Box w="150px" align="center" mr="16px" ml="16px" pt="4px" pb="4px" bg="#050A30" borderRadius="10px">
                            Manage Members
                        </Box>:
                        <Box w="150px" align="center" m="4px">
                            Manage Members
                        </Box>}
                    </NavLink>
                </ListItem>
                <ListItem>
                    <NavLink to={`/community/${cid}/admin/settings`}>
                    {choice === 3?
                        <Box w="150px" align="center" mr="16px" ml="16px" pt="4px" pb="4px" bg="#050A30" borderRadius="10px">
                            Settings
                        </Box>:
                        <Box w="150px" align="center" m="4px">
                            Settings
                        </Box>}
                    </NavLink>
                </ListItem>
            </List>
        </Flex>
    );
}

export default CommunityAdminSideBar;