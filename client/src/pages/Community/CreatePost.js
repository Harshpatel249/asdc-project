import { Box, Button, FormControl, FormLabel, Heading, Input, Textarea } from "@chakra-ui/react";
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';


const CreatePost = () => {
    const [postTitle, setPostTitle] = useState("");
  const [postDescription, setPostDescription] = useState("");
  const [postImage, setPostImage] = useState(null);
    const [loading, setLoading] = useState(false);
    const [postList, setPostList] = useState([]);
    const userid = localStorage.getItem('userID');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const getOptions = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('BearerToken')
                }
            }

            try {
                setLoading(true);
                console.log('reached');
                const response = await fetch('https://commune-dev-csci5308-server.onrender.com/posts/', getOptions);
                console.log('response: ', response);
                if (response.ok) {
                    const responseData = await response.json();
                    console.log(responseData);
                    // setPostList(responseData);
                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, []);

    const handleTitleChange = (post) => {
        setPostTitle(post.target.value);
    };

    const handleDescriptionChange = (post) => {
        setPostDescription(post.target.value);
    };

    const handleTest = (post) => {
        setPostImage(post.target.value);
    };



    const handleSubmit = async (post) => {
        console.log('here');
        post.preventDefault();
        // Perform the create community logic here
        console.log('Title:', postTitle);
        console.log('Description:', postDescription);
        console.log('Image: ', postImage);

        try {

            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('BearerToken')
                },
                body: JSON.stringify({
                    postTitle: postTitle,
                    description: postDescription,
                    postImage: "abc",
                    userId: 1,
                    communityId: 5
                })
            };

            const response = await fetch('https://commune-dev-csci5308-server.onrender.com/posts', requestOptions);

            // const communityID = await response.text();
            // console.log("community id: " + communityID);


            if (response.ok) {
                navigate(`/posts`);
            } else {

            }

        } catch (error) {
            console.error(error);
        }
    };

    return (
        <Box className="main-div">
            <Box className="container-div">
                <Heading>Create a new post</Heading>
                <div style = {{width:"90%"}}>
                    <FormControl>
                        <form onSubmit={handleSubmit}>
                                <FormControl mt={4}>
                                <FormLabel>Post Title</FormLabel>
                                <Input
                                    type="text"
                                    value={postTitle}
                                    onChange= {handleTitleChange}
                                />
                                </FormControl>

                                <FormControl mt={4}>
                                <FormLabel>Post Description</FormLabel>
                                <Textarea
                                    value={postDescription}
                                    onChange={handleDescriptionChange}
                                />
                                </FormControl>

                                <FormControl mt={4}>
                                <FormLabel>Post Image</FormLabel>
                                    {/* <Input type="file"/> */}
                                    <Textarea
                                    value="abc"
                                    onChange={handleTest}
                                /> 
                                </FormControl>

                            </form>
                        </FormControl>
                        </div>
                    <Button variant="solid" type="submit" marginTop="32px" onClick={handleSubmit} >
                        Create
                    </Button>
                
            </Box>
        </Box>
    );
};

export default CreatePost;