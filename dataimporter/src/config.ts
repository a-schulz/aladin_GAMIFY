import axios, {AxiosRequestConfig, AxiosResponse} from "axios";

require('dotenv').config();

export const serviceURL = process.env.SKILLS_SERVICE_URL;
export const username = process.env.SKILLS_USERNAME;
export const password = process.env.SKILLS_PASSWORD;

const getAuthToken = async () => {
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
    };

    const params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);

    const config: AxiosRequestConfig = {
        headers,
    };

    const response: AxiosResponse<string> = await axios.post<string>(
        `${serviceURL}/performLogin`,
        params.toString(),
        config
    );

    if (response.status === 200) {
        return response.data;
    } else {
        console.error('Login request failed');
    }

}

export const authorizationHeaders = {
    Authorization: `Bearer ${getAuthToken()}`,
};