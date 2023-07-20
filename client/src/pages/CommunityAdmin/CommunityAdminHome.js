import { Box, Button, Flex, Skeleton, Text } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { NavLink, useParams } from 'react-router-dom';
import CommunityAdminSideBar from '../../components/SideBar/CommunityAdminSideBar';

function CommunityAdminHome() {
    const choice = 1;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const [interests, setInterests] = useState([]);


    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`);

                const interestResponse = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/interest`);

                if (response.ok && interestResponse.ok) {
                    const responseData = await response.json();
                    const interestResponseData = await interestResponse.json();


                    setCommunityDetails(responseData);
                    setInterests(interestResponseData);

                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [cid]);



    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunityAdminSideBar choice={choice} />
            </Flex>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        <Text fontSize="3xl" fontWeight="medium" mt="16px">Welcome to {communityDetails.name}</Text>
                        <Text fontSize="xl" fontWeight="medium" mt="24px">{communityDetails.description}</Text>
                        <Text fontSize="xl" fontWeight="medium" mt="24px">Community Interests: </Text>
                        <Flex wrap="wrap" w="300px" gap="16px">
                            {interests.map((item, key) => (
                                <Text fontWeight="medium">{item.interestName}</Text>
                            ))}
                        </Flex>

                        <NavLink to={"/community/"+cid+"/admin/edit"}>
                            <Button>Edit info</Button>
                        </NavLink>

                    </Flex>
                }

            </Box>
        </Flex>
    );
}

export default CommunityAdminHome;