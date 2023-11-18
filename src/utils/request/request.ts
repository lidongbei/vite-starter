import axios, { AxiosRequestConfig, AxiosResponse, AxiosInstance } from 'axios'

/**
 * 请求类
 */
export class Request<R = any> {
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
    public request(config: AxiosRequestConfig): Promise<AxiosResponse<R>> {
        return this.instance.request(config)
    }

    /**
     * GET 请求
     * @param url 服务地址
     * @param params url参数
     * @param config 请求配置对象
     * @returns 响应信息
     */
    public get(
        url: string,
        params?: any,
        config?: AxiosRequestConfig
    ) : Promise<AxiosResponse<R>> {
        let innerConfig: AxiosRequestConfig;
        if (config) {
            innerConfig = config
            config.params = params
        } else {
            innerConfig = { params }
        }
        return this.instance.get(url, innerConfig)
    }

    /**
     * POST 请求
     * @param url 服务地址
     * @param params url参数
     * @param config 请求配置对象
     * @returns 响应信息
     */
    public post(
        url: string,
        data?: any,
        config?: AxiosRequestConfig
    ) : Promise<AxiosResponse<R>> { 
        return this.instance.post(url, data, config)
    }

    /**
     * PUT 请求
     * @param url 服务地址
     * @param params url参数
     * @param config 请求配置对象
     * @returns 响应信息
     */
    public put(
        url: string,
        data?: any,
        config?: AxiosRequestConfig
    ) : Promise<AxiosResponse<R>> { 
        return this.instance.put(url, data, config)
    }

    /**
     * DELETE 请求
     * @param url 服务地址
     * @param params url参数
     * @param config 请求配置对象
     * @returns 响应信息
     */
    public delete(
        url: string,
        params?: any,
        config?: AxiosRequestConfig
    ) : Promise<AxiosResponse<Result<T>>> {
        let innerConfig: AxiosRequestConfig;
        if (config) {
            innerConfig = config
            config.params = params
        } else {
            innerConfig = { params }
        }
        return this.instance.delete(url, innerConfig)
    }
}

/**
 * 基础响应信息结构
 */
export type Result<T> = {
    code: number,
    message: string,
    data: T
}

/**
 * 创建请求
 * @param config 配置参数
 * @returns 
 */
export const createRequest = <T> (config: AxiosRequestConfig = {}) => {
    return new Request<Result<T>>(config)
}