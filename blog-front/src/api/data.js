import { axios } from '@/util/request'

/**
 * getBlogList api
 * @param parameter
 * @returns {*}
 */
export function getBlogList (parameter) {
  return axios({
    url: '/data/blog.json',
    method: 'get',
    data: parameter
  })
}
