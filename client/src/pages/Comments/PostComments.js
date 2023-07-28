import { Box, Flex, SimpleGrid, Text, Heading, Skeleton} from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import CommunitySideBar from '../../components/SideBar/CommunitySideBar';

function PostComments() {
    const choice = 2;
    const postId = 3;
    const [commentData, setCommentsData] = useState([]);
    const [loading, setLoading] = useState(true);



    useEffect(() => {
        const fetchData = async () => {
            try {
                const requestOptions = {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': localStorage.getItem('BearerToken')
                    }
                };
     
            

                const response = await fetch(`http://localhost:8080/comments/list/${postId}`, requestOptions);
                const data = await response.json();
                console.log(data);
                // setCommunityDetails(responseData);
                setCommentsData(data);
            
                
            } catch (error) {
                console.error("Error fetching posts:", error);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []); 

    const PostCard = ({ title, commentId }) => (
        <Box p={4} borderWidth="1px" borderRadius="lg" boxShadow="md" marginLeft={"15px"}>
          <Heading as="h3" size="md" mb={2}>
            {title}
          </Heading>
          
          <Text>{commentId}</Text>
          <Flex justifyContent="flex-end" p="2">
          {/* <Button backgroundColor={'red'} size="sm" onClick={() => onDelete()}> */}
          {/* </Button> */}
      </Flex>
        </Box>
    );

    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunitySideBar choice={choice} />
               
            </Flex>
            <div>
                <h1>All Comments</h1>
                <SimpleGrid columns={{ base: 1, md: 2, lg: 3 }} spacing={6}>
                {commentData.map((post) => (
                    <PostCard
                        key={post.commentId}
                        title={post.postTitle}
                    />
                    ))}
                </SimpleGrid>
            </div>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        POSTS Page
                    </Flex>
                }
            </Box>
            
        </Flex>
    );
}

export default PostComments;