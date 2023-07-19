import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import {
  ChakraProvider,
  theme
} from '@chakra-ui/react';
import './App.css';
import LayoutWithNav from './pages/Layout/LayoutWithNav';
import LayoutWithoutNav from './pages/Layout/LayoutWithoutNav';
import LandingPage from './pages/LandingPage/LandingPage';
import Login from './pages/UserAuthentication/Login';
import Onboarding from './pages/OnboardingPage/Onboarding';
import CreateCommunity from './pages/Community/CreateCommunity';
import CommunityHome from './pages/Community/CommunityHome';

const router = createBrowserRouter([
  {
    element: <LayoutWithNav />,
    children: [
      {
        path: "/",
        element: <LandingPage />
      },
      {
        path: "/create-community",
        element: <CreateCommunity />
      },
      {
        path: "/community/:cid/",
        element: <CommunityHome />
      }
    ]
  },
  {
    element: <LayoutWithoutNav />,
    children: [
      {
        path: "/user/login",
        element: <Login />
      },
      {
        path: "/onboarding",
        element: <Onboarding />
      },
    ]
  }
]);

function App() {
  return (
    <div className="App">
      <ChakraProvider theme={theme}>
        <RouterProvider router={router} />
      </ChakraProvider>
    </div>
  );
}

export default App;
