import { Flex, Skeleton, Text } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';


function CommunityMembers() {
    let { cid } = useParams();
    const [loading, setLoading] = useState(true);
    const [members, setMembers] = useState([]);
    
    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members`);

                if (response.ok) {
                    const responseData = await response.json();

                    setMembers(responseData);
                    setLoading(false);
                }

            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, [cid]);

    return (
        <Flex flexDirection="column" alignItems="center" w="100%" mr="32px">
            <Text fontWeight="medium" mt="16px">Members</Text>
            <Flex flexDirection="column" borderColor="black" border="2px" borderRadius="10px" w="100%" justifyContent="start" minH="69vh">
                {
                    loading ? <Skeleton /> :
                        members.map((item, key) => (
                            <Flex w="100%" gap="8px" justifyContent="center" mt="8px">
                                <Text fontWeight="medium">{item.user_name}</Text>
                                <Text fontWeight="medium">|{item.user_role}|</Text>
                            </Flex>
                        ))
                }
            </Flex>
        </Flex>
    );
}

export default CommunityMembers;