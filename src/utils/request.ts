import axios, { AxiosRequestConfig, AxiosResponse, AxiosInstance } from 'axios'

/**
 * 请求类
 */
export class Request {
    instance: AxiosInstance

    defaultConfig: AxiosRequestConfig = { baseURL: '/api', timeout: 10 * 1000 }

    constructor(config: AxiosRequestConfig) {
        // 使用axios.create创建axios实例，配置为基础配置和我们传递进来的配置
        this.instance = axios.create(Object.assign(this.defaultConfig, config))
    }

    /**
     * 发送请求
     * @param config 请求参数配置
     * @returns 
     */
    public request<T = any>(config: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return this.instance.request(config)
    }
}