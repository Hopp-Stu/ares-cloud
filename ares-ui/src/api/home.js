import request from '@/utils/request'

export function getPanelGroups() {
    return request({
        url: '/ares/index/panelGroup',
        method: 'get'
    })
}

export function getLineChartData() {
    return request({
        url: '/ares/index/lineChartData',
        method: 'get',
    })
}
