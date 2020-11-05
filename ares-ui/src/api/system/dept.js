import request from '@/utils/request'

// 查询部门列表
export function listDept(query) {
  return request({
    url: '/ares/sysDept/list',
    method: 'get',
    params: query
  })
}

// 查询部门详细
export function getDept(deptId) {
  return request({
    url: '/ares/sysDept/' + deptId,
    method: 'get'
  })
}

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/ares/sysDept/treeselect',
    method: 'get'
  })
}


// 新增部门
export function addDept(data) {
  return request({
    url: '/ares/sysDept/edit',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept(data) {
  return request({
    url: '/ares/sysDept/edit',
    method: 'post',
    data: data
  })
}

// 删除部门
export function delDept(deptId) {
  return request({
    url: '/ares/sysDept/' + deptId,
    method: 'delete'
  })
}