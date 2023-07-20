import { Box, Flex, Skeleton, Text, Button } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CommunityAdminSideBar from '../../components/SideBar/CommunityAdminSideBar';

function CommunityAdminSettings() {
    const choice = 3;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();


    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`);

        
                if (response.ok ) {
                    const responseData = await response.json();
                    setCommunityDetails(responseData);

                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [cid]); 

    const handleDeleteCommunity = async () => {
        const RequestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' }
        }

        await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`, RequestOptions);

        navigate("/");
    };


    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunityAdminSideBar choice={choice} />
            </Flex>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        <Text mt="140px" fontSize="3xl" fontWeight="medium">
                            Welcome to settings page of {communityDetails.name}
                        </Text>
                        <Text>
                            Are you sure you want to delete the community?
                        </Text>
                        <Button onClick={handleDeleteCommunity}>Delete</Button>
                    </Flex>
                }

            </Box>
            
        </Flex>
    );
}

export default CommunityAdminSettings;